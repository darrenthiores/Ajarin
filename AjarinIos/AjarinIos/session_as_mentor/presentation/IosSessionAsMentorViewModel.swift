//
//  IosSessionAsMentorViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension SessionAsMentorScreen {
    @MainActor class IosSessionAsMentorViewModel: ObservableObject {
        private let viewModel: SessionAsMentorViewModel
        
        @Published var state: SessionAsMentorState = SessionAsMentorState(
            historySession: nil,
            isSessionLoading: false,
            isSessionError: nil,
            user: nil,
            isUserLoading: false,
            isUserError: nil,
            sessionInfo: nil,
            sessionInfoLoading: false,
            sessionInfoError: nil,
            isSessionInfoEditable: false,
            mainLinkText: "",
            backupLinkText: "",
            materialLinkText: ""
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getOrderById: GetOrderById
            @Inject var getUserById: GetUserById
            
            self.viewModel = SessionAsMentorViewModel(
                getOrderById: getOrderById,
                getUserById: getUserById,
                coroutineScope: nil
            )
        }
        
        func initSession(
            sessionId: String,
            userId: String
        ) {
            viewModel.doInit(
                sessionId: sessionId,
                userId: userId
            )
        }
        
        func onEvent(event: SessionAsMentorEvent) {
            viewModel.onEvent(event: event)
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
    }
}
