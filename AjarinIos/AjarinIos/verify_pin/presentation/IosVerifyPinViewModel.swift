//
//  IosVerifyPinViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension VerifyPinScreen {
    @MainActor class IosVerifyPinViewModel: ObservableObject {
        private let viewModel: VerifyPinViewModel
        
        @Published var state: VerifyPinState = VerifyPinState(
            pin: "",
            pinError: nil,
            verifyPinLoading: false,
            verifyPinSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = VerifyPinViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: VerifyPinEvent) {
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
