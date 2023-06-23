//
//  PaymentMethodField.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PaymentMethodField: View {
    let value: PaymentMethod?
    let title: String
    let onClick: () -> Void
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                if let logo = value?.getLogo() {
                    Image(logo)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 50, height: 50)
                    
                    Spacer()
                        .frame(width: 32)
                }
                
                Text(value?.name ?? title)
                
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

struct PaymentMethodField_Previews: PreviewProvider {
    static var previews: some View {
        PaymentMethodField(
            value: nil,
            title: "Select Method",
            onClick: { }
        )
    }
}
