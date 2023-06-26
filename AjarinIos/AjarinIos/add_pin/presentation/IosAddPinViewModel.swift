//
//  IosAddPinViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension AddPinScreen {
    @MainActor class IosAddPinViewModel: ObservableObject {
        private let viewModel: AddPinViewModel
        
        @Published var state: AddPinState = AddPinState(
            pin: "",
            pinError: nil,
            addPinLoading: false,
            addPinSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = AddPinViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: AddPinEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.addPinSuccess {
                        let userDefaults = UserDefaults.standard
                        userDefaults.set(true, forKey: "hasPin")
                    }
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
