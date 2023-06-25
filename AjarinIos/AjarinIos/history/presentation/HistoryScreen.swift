//
//  HistoryScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HistoryScreen: View {
    let isMentor: Bool = true
    
    @StateObject private var viewModel = IosHistoryViewModel()
    @State private var selectedTab: HistoryTab = .user
    
    var body: some View {
        NavigationStack {
            if isMentor {
                VStack {
                    HStack {
                        TabButton(
                            title: "User",
                            onClick: {
                                selectedTab = .user
                            },
                            isSelected: selectedTab == .user
                        )
                        
                        TabButton(
                            title: "Mentor",
                            onClick: {
                                selectedTab = .mentor
                            },
                            isSelected: selectedTab == .mentor
                        )
                    }
                    .font(.headline)
                    
                    TabView(
                        selection: $selectedTab
                    ) {
                        ScrollView {
                            ForEach(viewModel.state.historySessions, id: \.id) { history in
                                NavigationLink {
                                    SessionAsUserScreen(
                                        sessionId: history.id,
                                        mentorId: history.mentorId
                                    )
                                } label: {
                                    MentorHistoryCard(history: history)
                                }
                                .buttonStyle(.plain)
                            }
                            .padding()
                        }
                        .tag(HistoryTab.user)
                        
                        ScrollView {
                            ForEach(viewModel.state.historySessions, id: \.id) { history in
                                NavigationLink {
                                    SessionAsMentorScreen(
                                        sessionId: history.id,
                                        userId: history.userId
                                    )
                                } label: {
                                    UserHistoryCard(history: history)
                                }
                                .buttonStyle(.plain)
                            }
                            .padding()
                        }
                        .tag(HistoryTab.mentor)
                    }
                    .tabViewStyle(.page(indexDisplayMode: .never))
                    .navigationTitle("History")
                    .onAppear {
                        viewModel.startObserving()
                    }
                    .onDisappear {
                        viewModel.dispose()
                    }
                }
            } else {
                ScrollView {
                    ForEach(viewModel.state.historySessions, id: \.id) { history in
                        NavigationLink {
                            SessionAsUserScreen(
                                sessionId: history.id,
                                mentorId: history.mentorId
                            )
                        } label: {
                            MentorHistoryCard(history: history)
                        }
                        .buttonStyle(.plain)
                    }
                    .padding()
                }
                .navigationTitle("History")
                .onAppear {
                    viewModel.startObserving()
                }
                .onDisappear {
                    viewModel.dispose()
                }
            }
        }
    }
}

struct HistoryScreen_Previews: PreviewProvider {
    static var previews: some View {
        HistoryScreen()
    }
}
