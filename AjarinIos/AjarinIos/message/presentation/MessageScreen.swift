//
//  MessageScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MessageScreen: View {
    let mentorId: String
    let userId: String
    
    @StateObject private var viewModel = IosMessageViewModel()
    @Environment(\.colorScheme) var colorScheme
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        VStack {
            HStack {
                Button {
                    dismiss()
                } label: {
                    Image(systemName: "chevron.left")
                }
                .buttonStyle(.plain)
                
                Spacer()
                    .frame(width: 16)
                
                AsyncImage(
                    url: URL(string: viewModel.state.mentor?.photoUrl ?? ""),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: 50,
                                height: 50
                            )
                            .clipShape(Circle())
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .frame(
                            width: 50,
                            height: 50
                        )
                        .background(.ultraThinMaterial)
                        .clipShape(Circle())
                    }
                )
                
                Spacer()
                    .frame(width: 16)
                
                Text(viewModel.state.mentor?.name ?? "Loading...")
                
                Spacer()
            }
            .padding(.horizontal)
            
            Divider()
            
            ScrollViewReader { scrollView in
                ReversedScrollView(.vertical) {
                    ForEach(viewModel.state.messages.reversed(), id: \.id) { message in
                        HStack {
                            if message.sentToId == mentorId {
                                Spacer()
                                PersonalMessageItem(text: message.content)
                            } else {
                                FriendMessageItem(text: message.content)
                                Spacer()
                            }
                        }
                    }
                }
                .padding(.horizontal)
                .onChange(of: viewModel.state.messages) { messages in
                    if let message = messages.first {
                        withAnimation(.default) {
                            scrollView
                                .scrollTo(message.id)
                        }
                    }
                }
            }
            
            HStack {
                TextField(
                    "Message",
                    text: Binding(
                        get: {
                            viewModel.state.newMessage
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnMessageChange(newText: $0)
                            )
                        }
                    ),
                    axis: .vertical
                )
                .padding()
                .overlay(
                    Rectangle()
                        .strokeBorder(
                            .gray.opacity(0.2),
                            style: StrokeStyle(lineWidth: 2.0)
                        )
                )
                .lineLimit(10)
                
                Button {
                    viewModel.onEvent(
                        event: .SendMessage(userId: userId)
                    )
                } label: {
                    Image(systemName: "arrowtriangle.right.fill")
                        .font(.title)
                }
            }
            .padding()
        }
        .navigationBarBackButtonHidden()
        .onAppear {
            viewModel.startObserving()
            viewModel.initMessage(
                mentorId: mentorId,
                userId: userId
            )
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct MessageScreen_Previews: PreviewProvider {
    static var previews: some View {
        MessageScreen(
            mentorId: "M1",
            userId: "U1"
        )
    }
}
