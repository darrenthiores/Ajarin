//
//  IosBookingViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension BookingScreen {
    @MainActor class IosBookingViewModel: ObservableObject {
        private let viewModel: BookingViewModel
        
        @Published var state: BookingState = BookingState(
            mentor: nil,
            isFetching: false,
            isError: nil,
            isCourseDropDownOpen: false,
            course: nil,
            courseError: nil,
            date: nil,
            dateError: nil,
            schedule: nil,
            scheduleError: nil,
            paymentMethod: nil,
            paymentMethodError: nil,
            bookingSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getMentorById: GetMentorById
            
            self.viewModel = BookingViewModel(
                getMentorById: getMentorById,
                coroutineScope: nil
            )
        }
        
        func initMentor(id: String) {
            viewModel.doInitMentor(id: id)
        }
        
        func onEvent(event: BookingEvent) {
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
