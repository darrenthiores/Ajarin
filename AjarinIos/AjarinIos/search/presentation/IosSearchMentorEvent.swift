//
//  IosSearchMentorEvent.swift
//  AjarinIos
//
//  Created by Darren Thiores on 20/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

enum IosSearchMentorEvent {
    case OnSearch
    case FetchMentor
    case SelectCourse(course: Course)
    case Clear
}
