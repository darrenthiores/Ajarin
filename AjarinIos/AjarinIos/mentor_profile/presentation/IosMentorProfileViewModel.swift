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
        @Inject var getReviews: GetMentorReviews
        
        @Published var state: MentorProfileState = MentorProfileState(
            mentor: nil,
            isFetching: false,
            isError: nil
        )
        @Published var iosState: IosMentorProfileState = IosMentorProfileState()
        
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
            fetchReviews(id: id)
        }
        
        func iosOnEvent(event: IosMentorProfileEvent) {
            switch event {
            case .FetchReviews(id: let id):
                fetchReviews(id: id)
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
        
        func fetchReviews(id: String) {
            Task {
                iosState.isFetchingReviews = true
                
                do {
                    let result = try await getReviews.invoke(
                        id: id,
                        page: Int32(iosState.currentReviewPage)
                    )
                    
                    iosState.isFetchingReviews = false
                    
                    if result.isEmpty {
                        iosState.endReviewReached = true
                    } else {
                        iosState.reviews.append(contentsOf: result)
                        iosState.currentReviewPage += 1
                    }
                    
                    print("fetch mentors success: \(iosState.currentReviewPage-1)")
                } catch let error {
                    iosState.endReviewReached = true
                    iosState.isFetchingReviews = false
                    iosState.reviewError = error
                    
                    print("fetch mentors failed: \(error): \(iosState.currentReviewPage)")
                }
            }
        }
    }
}
