//
//  CourseItem.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CourseItem: View {
    let text: String
    
    var body: some View {
        Text(text)
            .padding(8)
            .font(.caption)
            .background(.primary.opacity(0.2))
            .cornerRadius(8)
            .overlay(
                RoundedRectangle(cornerRadius: 8)
                    .stroke(
                        .primary,
                        lineWidth: 1
                    )
            )
            .foregroundColor(.primary)
    }
}

struct CourseItem_Previews: PreviewProvider {
    static var previews: some View {
        CourseItem(
            text: "Mathematic"
        )
    }
}
