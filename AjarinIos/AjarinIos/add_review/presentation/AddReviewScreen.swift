//
//  AddReviewScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import _PhotosUI_SwiftUI

struct AddReviewScreen: View {
    let sessionId: String
    
    @StateObject private var viewModel = IosAddReviewViewModel()
    @State private var photoSheetOpen = false
    @State private var selectedItem: PhotosPickerItem? = nil
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        List {
            ScrollView(.horizontal) {
                HStack {
                    Button {
                        photoSheetOpen = true
                    } label: {
                        Rectangle()
                            .frame(width: 100, height: 100)
                            .foregroundColor(.gray)
                            .overlay {
                                Image(systemName: "plus")
                            }
                            .cornerRadius(4)
                    }
                    .buttonStyle(.plain)
                    
                    ForEach(viewModel.selectedImages) { image in
                        Button {
                            viewModel.removeImage(image: image)
                        } label: {
                            ZStack(alignment: .topTrailing) {
                                Image(
                                    uiImage: image.image
                                )
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: 200, height: 200)
                                .cornerRadius(4)

                                Image(systemName: "x.circle.fill")
                                    .foregroundColor(.white)
                                    .font(.title)
                                    .padding(10)
                            }
                        }
                    }
                }
            }
            .listRowSeparator(.hidden)
            
            Section("Review") {
                TextField(
                    "Tell People Your Experience",
                    text: Binding(
                        get: {
                            viewModel.state.reviewText
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnReviewChange(newText: $0)
                            )
                        }
                    ),
                    axis: .vertical
                )
                .lineLimit(5)
            }
            .listRowSeparator(.hidden)
            
            Section("Rating") {
                HStack {
                    ForEach(1..<6) { i in
                        Button {
                            viewModel.onEvent(
                                event: .OnPickRating(rating: Int32(i))
                            )
                        } label: {
                            Image(
                                systemName: (viewModel.state.rating >= i) ? "star.fill" : "star"
                            )
                            .foregroundColor(
                                (viewModel.state.rating >= i) ? .yellow : .gray
                            )
                        }
                    }
                }
                .buttonStyle(.plain)
            }
            .listRowSeparator(.hidden)
            
            Section("Mentor to be Reviewed") {
                ReviewMentorCard(
                    mentorPhotoUrl: viewModel.state.historySession?.mentorImgUrl,
                    mentorName: viewModel.state.historySession?.mentorName,
                    mentorPrice: viewModel.state.historySession?.mentorPrice,
                    courseName: viewModel.state.historySession?.course.name
                )
            }
            .listRowSeparator(.hidden)
        }
        .listStyle(.plain)
        .navigationTitle("Add Review")
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    viewModel.onEvent(
                        event: .OnPostReview()
                    )
                } label: {
                    Text("Post")
                }
                .buttonStyle(.plain)
                .disabled(
                    viewModel.state.isPosting ||
                    viewModel.state.reviewText.isEmpty ||
                    viewModel.state.rating == 0
                )
            }
        }
        .photosPicker(
            isPresented: $photoSheetOpen,
            selection: $selectedItem,
            matching: .images
        )
        .onChange(of: selectedItem) { item in
            Task {
                if let data = try? await item?.loadTransferable(
                    type: Data.self
                ) {
                    if let image = UIImage(data: data) {
                        viewModel.addImage(image: image)
                    }
                } else {
                    print("data null")
                }
            }
        }
        .onAppear {
            viewModel.startObserving()
            viewModel.initSession(sessionId: sessionId)
        }
        .onDisappear {
            viewModel.dispose()
        }
        .onChange(of: viewModel.state.isPostSuccess) { result in
            if result {
                dismiss()
            }
        }
    }
}

struct AddReviewScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddReviewScreen(
            sessionId: "H1"
        )
    }
}
