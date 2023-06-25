//
//  InboxScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct InboxScreen: View {
    let userId: String
    
    @StateObject private var viewModel = IosInboxViewModel()
    
    var body: some View {
        List {
            ForEach(viewModel.state.inbox, id: \.inboxId) { message in
                NavigationLink {
                    MessageScreen(
                        mentorId: message.mentor.id,
                        userId: userId
                    )
                } label: {
                    HStack {
                        AsyncImage(
                            url: URL(string: message.mentor.photoUrl),
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
                        
                        VStack(alignment: .leading) {
                            Text(message.mentor.name)
                                .fontWeight(.semibold)
                                .lineLimit(1)
                            
                            Text(message.lastMessage)
                                .font(.caption)
                                .foregroundColor(.gray)
                                .lineLimit(1)
                        }
                    }
                }
            }
        }
        .listStyle(.plain)
        .navigationTitle("Inbox")
        .onAppear {
            viewModel.startObserving()
            viewModel.initInbox(userId: userId)
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct InboxScreen_Previews: PreviewProvider {
    static var previews: some View {
        InboxScreen(
            userId: "U1"
        )
    }
}
