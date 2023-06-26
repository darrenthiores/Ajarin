//
//  BankAccountSheet.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BankAccountSheet: View {
    let accounts: [BankAccount]
    let onClick: (BankAccount) -> Void
    
    var body: some View {
        List {
            Text("Select Account")
                .padding(.vertical)
                .listRowSeparator(.hidden)
            
            ForEach(accounts, id: \.id) { account in
                Button {
                    onClick(account)
                } label: {
                    BankAccountItem(account: account)
                }
                .buttonStyle(.plain)
            }
            .onDelete { indexSet in
                print(indexSet)
            }
        }
        .listStyle(.plain)
    }
}

struct BankAccountSheet_Previews: PreviewProvider {
    static var previews: some View {
        BankAccountSheet(
            accounts: [],
            onClick: { _ in }
        )
    }
}
