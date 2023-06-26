//
//  IosApplyAsMentorState.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct IosApplyAsMentorState {
    var isSelectingIDFile: Bool = false
    var idFile: URL? = nil
    var isSelectingInstitutionFile: Bool = false
    var institutionFile: URL? = nil
    var isSelectingGradeFile: Bool = false
    var gradeFile: URL? = nil
    var isSelectingCourses = false
    var isSelectingSchedules = false
}
