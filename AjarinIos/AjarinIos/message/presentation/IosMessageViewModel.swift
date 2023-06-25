//
//  IosMessageViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension MessageScreen {
    @MainActor class IosMessageViewModel: ObservableObject {
        private let viewModel: MessageViewModel
        
        @Published var state: MessageState = MessageState(
            messages: [],
            messagesLoading: false,
            messagesError: nil,
            mentor: nil,
            mentorLoading: false,
            mentorError: nil,
            newMessage: "",
            error: nil,
            isSendingMessage: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getMessagesById: GetMessagesById
            @Inject var getMentorById: GetMentorById
            @Inject var createInbox: CreateInbox
            @Inject var insertMessage: InsertMessage
            
            self.viewModel = MessageViewModel(
                getMessageById: getMessagesById,
                getMentorById: getMentorById,
                createInbox: createInbox,
                insertMessage: insertMessage,
                coroutineScope: nil
            )
        }
        
        func initMessage(
            mentorId: String,
            userId: String
        ) {
            viewModel.doInit(
                mentorId: mentorId,
                userId: userId
            )
        }
        
        func onEvent(event: MessageEvent) {
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
