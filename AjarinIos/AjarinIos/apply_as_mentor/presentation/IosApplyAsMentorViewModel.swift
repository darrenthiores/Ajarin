//
//  IosApplyAsMentorViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension ApplyAsMentorScreen {
    @MainActor class IosApplyAsMentorViewModel: ObservableObject {
        private let viewModel: ApplyAsMentorViewModel
        
        @Published var state: ApplyAsMentorState = ApplyAsMentorState(
            fullName: "",
            fullNameError: nil,
            id: "",
            idError: nil,
            institutionName: "",
            institutionError: nil,
            lastEducation: nil,
            isEducationDropDownOpen: false,
            selectedCourses: [],
            coursesError: nil,
            selectedSchedule: [],
            proposedFee: "",
            proposedFeeError: nil,
            isApplying: false,
            applyError: nil,
            applySuccess: false
        )
        
        @Published var iosState: IosApplyAsMentorState = IosApplyAsMentorState()
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var validateId: ValidateId
            
            self.viewModel = ApplyAsMentorViewModel(
                validateId: validateId,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: ApplyAsMentorEvent) {
            viewModel.onEvent(event: event)
        }
        
        func onEvent(event: IosApplyAsMentorEvent) {
            switch event {
            case .selectIdFile(file: let file):
                iosState.idFile = file
            case .selectInstitutionFile(file: let file):
                iosState.institutionFile = file
            case .selectGradeFile(file: let file):
                iosState.gradeFile = file
            case .openIdPicker:
                iosState.isSelectingIDFile = true
            case .openInstitutionPicker:
                iosState.isSelectingInstitutionFile = true
            case .openGradePicker:
                iosState.isSelectingGradeFile = true
            case .openCoursesSheet:
                iosState.isSelectingCourses = true
            case .openSchedulesSheet:
                iosState.isSelectingSchedules = true
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
    }
}
