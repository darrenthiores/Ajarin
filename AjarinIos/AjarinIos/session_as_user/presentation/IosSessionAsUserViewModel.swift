//
//  IosSessionAsUserViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension SessionAsUserScreen {
    @MainActor class IosSessionAsUserViewModel: ObservableObject {
        private let viewModel: SessionViewModel
        
        @Published var state: SessionState = SessionState(
            historySession: nil,
            isSessionLoading: false,
            isSessionError: nil,
            mentor: nil,
            isMentorLoading: false,
            isMentorError: nil,
            sessionInfo: nil,
            sessionInfoLoading: false,
            sessionInfoError: nil
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getOrderById: GetOrderById
            @Inject var getMentorById: GetMentorById
            
            self.viewModel = SessionViewModel(
                getOrderById: getOrderById,
                getMentorById: getMentorById,
                coroutineScope: nil
            )
        }
        
        func initSession(
            sessionId: String,
            mentorId: String
        ) {
            viewModel.doInit(
                sessionId: sessionId,
                mentorId: mentorId
            )
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
