//
//  BankAccountItem.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BankAccountItem: View {
    let account: BankAccount
    
    var body: some View {
        HStack {
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
            
            Spacer()
        }
    }
}

struct BankAccountItem_Previews: PreviewProvider {
    static var previews: some View {
        BankAccountItem(
            account: BankAccount(
                id: "0",
                name: "Darren",
                accountNumber: "021017000",
                bank: Bank(
                    bankId: "1",
                    bankName: "BCA"
                )
            )
        )
    }
}
