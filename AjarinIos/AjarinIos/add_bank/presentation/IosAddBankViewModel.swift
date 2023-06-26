//
//  IosAddBankViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension AddBankScreen {
    @MainActor class IosAddBankViewModel: ObservableObject {
        private let viewModel: AddBankViewModel
        
        @Published var state: AddBankState = AddBankState(
            selectedBank: nil,
            selectedBankError: nil,
            isBankDropDownOpen: false,
            accountNumber: "",
            accountNumberError: nil,
            isSaving: false,
            saveSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = AddBankViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: AddBankEvent) {
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
