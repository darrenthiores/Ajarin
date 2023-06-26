//
//  SelectCoursesSheet.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

let allCourses = [
    Course(
        id: "1",
        name: "B. Indonesia"
    ),
    Course(
        id: "2",
        name: "Biologi"
    ),
    Course(
        id: "3",
        name: "Fisika"
    ),
    Course(
        id: "4",
        name: "Kimia"
    ),
    Course(
        id: "5",
        name: "Matematika"
    ),
    Course(
        id: "6",
        name: "Ekonomi"
    ),
    Course(
        id: "7",
        name: "Geografi"
    ),
    Course(
        id: "8",
        name: "Akuntansi"
    ),
    Course(
        id: "9",
        name: "Sejarah"
    ),
    Course(
        id: "10",
        name: "Sosiologi"
    )
]

struct SelectCoursesSheet: View {
    let selectedCourses: [Course]
    let onClick: (Course) -> Void
    
    var body: some View {
        List {
            Text("Select Courses")
                .padding(.vertical)
                .listRowSeparator(.hidden)
            
            ForEach(allCourses, id: \.id) { course in
                let isChecked = selectedCourses.contains(course)
                
                Button {
                    onClick(course)
                } label: {
                    HStack {
                        Text(course.name)
                        
                        Spacer()
                        
                        Image(systemName: isChecked ? "checkmark.square" : "square")
                    }
                    .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
            }
        }
        .listStyle(.plain)
    }
}

struct SelectCoursesSheet_Previews: PreviewProvider {
    static var previews: some View {
        SelectCoursesSheet(
            selectedCourses: [],
            onClick: { _ in }
        )
    }
}
