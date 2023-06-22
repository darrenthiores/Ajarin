//
//  IosMentorProfileViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension MentorProfileScreen {
    @MainActor class IosMentorProfileViewModel: ObservableObject {
        private let viewModel: MentorProfileViewModel
        
        @Published var state: MentorProfileState = MentorProfileState(
            mentor: nil,
            isFetching: false,
            isError: nil,
            reviews: []
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getMentorById: GetMentorById
            
            self.viewModel = MentorProfileViewModel(
                getMentorById: getMentorById,
                coroutineScope: nil
            )
        }
        
        func initMentor(id: String) {
            viewModel.doInitMentor(id: id)
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
