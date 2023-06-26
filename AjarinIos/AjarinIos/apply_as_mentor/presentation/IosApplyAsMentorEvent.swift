//
//  IosApplyAsMentorEvent.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum IosApplyAsMentorEvent {
    case openIdPicker
    case selectIdFile(file: URL)
    case openInstitutionPicker
    case selectInstitutionFile(file: URL)
    case openGradePicker
    case selectGradeFile(file: URL)
    case openCoursesSheet
    case openSchedulesSheet
}
