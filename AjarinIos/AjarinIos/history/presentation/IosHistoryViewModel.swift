//
//  IosHistoryViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HistoryScreen {
    @MainActor class IosHistoryViewModel: ObservableObject {
        private let viewModel: HistoryViewModel
        @Inject var getUserOrders: GetUserOrders
        @Inject var getMentorOrders: GetMentorOrders
        @Inject var getUser: GetUser
        
        @Published var state: HistoryState = HistoryState(
            isMentor: false,
            historyLoading: false,
            historyError: nil
        )
        @Published var iosState: IosHistoryState = IosHistoryState()
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = HistoryViewModel(
                coroutineScope: nil
            )
            
            initHistory()
        }
        
        func onEvent(event: HistoryEvent) {
            viewModel.onEvent(event: event)
        }
        
        func iosOnEvent(event: IosHistoryEvent) {
            switch event {
            case .FetchUserOrder:
                fetchUserOrders()
            case .FetchMentorOrder:
                fetchMentorOrders()
            }
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
        
        func initHistory() {
            Task {
                do {
                    let result = try await getUser.invoke()
                    let isMentor = result?.roleType == "2"
                    
                    onEvent(event: .OnUpdateIsMentor(isMentor: isMentor))
                    
                    fetchMentorOrders()
                } catch let error {
                    print(error)
                }
            }
        }
        
        func fetchUserOrders() {
            Task {
                iosState.isFetchingUserOrders = true
                
                do {
                    let result = try await getUserOrders.invoke(page: Int32(iosState.currentUserOrderPage))
                    iosState.isFetchingUserOrders = false
                    
                    if result.isEmpty {
                        iosState.endUserOrderReached = true
                    } else {
                        iosState.userOrders.append(contentsOf: result)
                        iosState.currentUserOrderPage += 1
                    }
                    
                    print("fetch mentors success: \(iosState.currentUserOrderPage-1)")
                } catch let error {
                    iosState.endUserOrderReached = true
                    iosState.isFetchingUserOrders = false
                    iosState.userOrderError = error
                    
                    print("fetch mentors failed: \(error): \(iosState.currentUserOrderPage)")
                }
            }
        }
        
        func fetchMentorOrders() {
            Task {
                iosState.isFetchingMentorOrders = true
                
                do {
                    let result = try await getMentorOrders.invoke(page: Int32(iosState.currentMentorOrdersPage))
                    iosState.isFetchingMentorOrders = false
                    
                    if result.isEmpty {
                        iosState.endMentorOrdersReached = true
                    } else {
                        iosState.mentorOrders.append(contentsOf: result)
                        iosState.currentMentorOrdersPage += 1
                    }
                    
                    print("fetch mentors success: \(iosState.currentMentorOrdersPage-1)")
                } catch let error {
                    iosState.endMentorOrdersReached = true
                    iosState.isFetchingMentorOrders = false
                    iosState.mentorOrderError = error
                    
                    print("fetch mentors failed: \(error): \(iosState.currentMentorOrdersPage)")
                }
            }
        }
    }
}
