//
//  CourseDropDown.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CourseDropDown: View {
    let courses: [Course]
    let course: Course?
    let isOpen: Bool
    let selectCourse: (Course) -> Void
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    courses,
                    id: \.id
                ) { availableCourse in
                    Button {
                        selectCourse(availableCourse)
                    } label: {
                        Text(availableCourse.name)
                    }
                    .buttonStyle(.plain)
                }
            }
        } label: {
            HStack {
                Text(course?.name ?? "Select Course")
                
                Spacer()
                
                Image(systemName: isOpen ? "chevron.up" : "chevron.down")
            }
            .padding()
            .background(
                Rectangle()
                    .fill(
                        colorScheme == .dark ? Color(UIColor.secondarySystemFill) : Color(UIColor.systemBackground)
                    )
                    .cornerRadius(4)
                    .shadow(radius: 2)
            )
        }
        .buttonStyle(.plain)
    }
}

struct CourseDropDown_Previews: PreviewProvider {
    static var previews: some View {
        CourseDropDown(
            courses: [],
            course: nil,
            isOpen: false,
            selectCourse: { _ in}
        )
    }
}
