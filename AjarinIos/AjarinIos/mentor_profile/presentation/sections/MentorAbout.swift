//
//  MentorAbout.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MentorAbout: View {
    let mentor: Mentor?
    let dummyDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    
    var body: some View {
        VStack(alignment: .leading) {
            Section {
                Text(mentor?.education ?? "Loading...")
            } header: {
                Text("Education")
                    .font(.title2)
            }
            
            Spacer()
                .frame(height: 16)
            
            Section {
                WrappingHStack(
                    horizontalSpacing: 8,
                    verticalSpacing: 8
                ) {
                    if let courses = mentor?.courses {
                        ForEach(courses, id: \.id) { course in
                            CourseItem(text: course.name)
                        }
                    } else {
                        Text("Loading...")
                    }
                }
            } header: {
                Text("Courses")
                    .font(.title2)
            }
            
            Spacer()
                .frame(height: 16)
            
            Section {
                Text("Rp. \(mentor?.price ?? "0") / Session")
            } header: {
                Text("Rate")
                    .font(.title2)
            }
            
            Spacer()
                .frame(height: 16)
            
            Section {
                Text(dummyDescription)
            } header: {
                Text("Description")
                    .font(.title2)
            }
        }
    }
}

struct MentorAbout_Previews: PreviewProvider {
    static var previews: some View {
        MentorAbout(
            mentor: nil
        )
    }
}
