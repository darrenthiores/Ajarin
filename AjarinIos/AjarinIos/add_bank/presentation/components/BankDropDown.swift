//
//  BankDropDown.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

let dummyBanks = [
    Bank(
        bankId: "1",
        bankName: "BCA"
    ),
    Bank(
        bankId: "2",
        bankName: "BNI"
    ),
    Bank(
        bankId: "3",
        bankName: "BRI"
    ),
    Bank(
        bankId: "4",
        bankName: "Mandiri"
    ),
    Bank(
        bankId: "5",
        bankName: "BSI"
    ),
    Bank(
        bankId: "6",
        bankName: "Commbank"
    )
]

struct BankDropDown: View {
    let bank: Bank?
    let isOpen: Bool
    let selectBank: (Bank) -> Void
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    dummyBanks,
                    id: \.bankId
                ) { bank in
                    Button {
                        selectBank(bank)
                    } label: {
                        HStack {
                            Image(bank.getImageName())
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            
                            Spacer()
                                .frame(width: 32)
                            
                            Text(bank.bankName)
                        }
                    }
                    .buttonStyle(.plain)
                }
            }
        } label: {
            HStack {
                if let bankImage = bank?.getImageName() {
                    Image(bankImage)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 50, height: 50)
                    
                    Spacer()
                        .frame(width: 32)
                }
                
                Text(bank?.bankName ?? "Select Bank")
                
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

struct BankDropDown_Previews: PreviewProvider {
    static var previews: some View {
        BankDropDown(
            bank: nil,
            isOpen: false,
            selectBank: { _ in}
        )
    }
}
