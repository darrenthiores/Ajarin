//
//  EducationDropDown.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

let dummyEducations = [
    "SMA 1",
    "SMA 2",
    "SMA 3",
    "S1",
    "S2"
]

struct EducationDropDown: View {
    let education: String?
    let isOpen: Bool
    let selectEducation: (String) -> Void
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    dummyEducations,
                    id: \.self
                ) { education in
                    Button {
                        selectEducation(education)
                    } label: {
                        Text(education)
                    }
                    .buttonStyle(.plain)
                }
            }
        } label: {
            HStack {
                Text(education ?? "Select Last or Current Education")
                
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

struct EducationDropDown_Previews: PreviewProvider {
    static var previews: some View {
        EducationDropDown(
            education: nil,
            isOpen: false,
            selectEducation: { _ in }
        )
    }
}
