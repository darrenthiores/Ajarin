//
//  BankField.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BankField: View {
    let value: BankAccount?
    let title: String
    let onClick: () -> Void
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                if let account = value {
                    Image(account.bank.getImageName())
                        .resizable()
                        .scaledToFit()
                        .frame(width: 50, height: 50)
                    
                    Spacer()
                        .frame(width: 32)
                    
                    VStack(alignment: .leading) {
                        Text(account.name)
                        
                        Text("\(account.bank.bankName) | ****\(String(account.accountNumber.suffix(5)))")
                    }
                } else {
                    Text(title)
                }
                
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

struct BankField_Previews: PreviewProvider {
    static var previews: some View {
        BankField(
            value: nil,
            title: "Select Bank",
            onClick: {  }
        )
    }
}
