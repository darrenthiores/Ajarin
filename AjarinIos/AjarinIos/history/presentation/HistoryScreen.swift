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
    @StateObject private var viewModel = IosHistoryViewModel()
    @State private var selectedTab: HistoryTab = .user
    
    var body: some View {
        NavigationStack {
            if viewModel.state.isMentor {
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
                            ForEach(viewModel.iosState.userOrders, id: \.id) { history in
                                NavigationLink {
                                    SessionAsUserScreen(
                                        sessionId: history.id,
                                        mentorId: history.mentorId
                                    )
                                } label: {
                                    MentorHistoryCard(history: history)
                                }
                                .buttonStyle(.plain)
                                .onAppear {
                                    let index = viewModel.iosState.userOrders.firstIndex(
                                        where: { userOrders in userOrders.id == history.id }
                                    )
                                    
                                    if let index = index {
                                        let count = index + 1
                                        
                                        if count == viewModel.iosState.userOrders.count
                                            && count % 15 == 0{
                                            if !viewModel.iosState.endUserOrderReached
                                                && !viewModel.iosState.isFetchingUserOrders {
                                                viewModel.iosOnEvent(event: .FetchUserOrder
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            .padding()
                        }
                        .tag(HistoryTab.user)
                        
                        ScrollView {
                            ForEach(viewModel.iosState.mentorOrders, id: \.id) { history in
                                NavigationLink {
                                    SessionAsMentorScreen(
                                        sessionId: history.id,
                                        userId: history.userId
                                    )
                                } label: {
                                    UserHistoryCard(history: history)
                                }
                                .buttonStyle(.plain)
                                .onAppear {
                                    let index = viewModel.iosState.mentorOrders.firstIndex(
                                        where: { orders in orders.id == history.id }
                                    )
                                    
                                    if let index = index {
                                        let count = index + 1
                                        
                                        if count == viewModel.iosState.mentorOrders.count
                                            && count % 15 == 0{
                                            if !viewModel.iosState.endMentorOrdersReached
                                                && !viewModel.iosState.isFetchingMentorOrders {
                                                viewModel.iosOnEvent(event: .FetchMentorOrder
                                                )
                                            }
                                        }
                                    }
                                }
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
                    ForEach(viewModel.iosState.userOrders, id: \.id) { history in
                        NavigationLink {
                            SessionAsUserScreen(
                                sessionId: history.id,
                                mentorId: history.mentorId
                            )
                        } label: {
                            MentorHistoryCard(history: history)
                        }
                        .buttonStyle(.plain)
                        .onAppear {
                            let index = viewModel.iosState.userOrders.firstIndex(
                                where: { userOrders in userOrders.id == history.id }
                            )
                            
                            if let index = index {
                                let count = index + 1
                                
                                if count == viewModel.iosState.userOrders.count
                                    && count % 15 == 0{
                                    if !viewModel.iosState.endUserOrderReached
                                        && !viewModel.iosState.isFetchingUserOrders {
                                        viewModel.iosOnEvent(event: .FetchUserOrder
                                        )
                                    }
                                }
                            }
                        }
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
