//
//  IosAddReviewViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension AddReviewScreen {
    @MainActor class IosAddReviewViewModel: ObservableObject {
        private let viewModel: AddReviewViewModel
        
        @Published var state: AddReviewState = AddReviewState(
            historySession: nil,
            isSessionLoading: false,
            isSessionError: nil,
            reviewText: "",
            rating: 0,
            isPosting: false,
            isPostSuccess: false,
            isPostError: nil
        )
        
        @Published var selectedImages: [ReviewImage] = []
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getOrderById: GetOrderById
            
            self.viewModel = AddReviewViewModel(
                getOrderById: getOrderById,
                coroutineScope: nil
            )
        }
        
        func addImage(image: UIImage) {
            selectedImages.append(
                ReviewImage(
                    id: UUID(),
                    image: image
                )
            )
        }
        
        func removeImage(image: ReviewImage) {
            if let index = selectedImages.firstIndex(where: { img in img.id == image.id }) {
                selectedImages.remove(at: index)
            }
        }
        
        func initSession(sessionId: String) {
            viewModel.doInit(sessionId: sessionId)
        }
        
        func onEvent(event: AddReviewEvent) {
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
