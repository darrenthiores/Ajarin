//
//  ClickableField.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ClickableField: View {
    let value: String?
    let title: String
    let onClick: () -> Void
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                Text(value ?? title)
                
                Spacer()
                
                Image(systemName: "chevron.right")
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

struct ClickableField_Previews: PreviewProvider {
    static var previews: some View {
        ClickableField(
            value: nil,
            title: "Test Field",
            onClick: {  }
        )
    }
}
