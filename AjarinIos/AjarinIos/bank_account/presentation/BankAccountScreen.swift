//
//  BankAccountScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BankAccountScreen: View {
    @StateObject private var viewModel = IosBankAccountViewModel()
    @AppStorage("hasPin") private var hasPin: Bool = true
    
    var body: some View {
        if hasPin {
            ZStack {
                List {
                    ForEach(viewModel.state.bankAccounts, id: \.id) { account in
                        BankAccountItem(account: account)
                    }
                    .onDelete { indexSet in
                        print(indexSet)
                    }
                }
                .listStyle(.plain)
                
                VStack {
                    Spacer()
                    
                    HStack {
                        Spacer()
                        
                        NavigationLink {
                            Text("Add")
                        } label: {
                            Text("+")
                                .font(.title2)
                                .foregroundColor(Color.white)
                                .padding()
                        }
                        .buttonStyle(.plain)
                        .background(.primary)
                        .clipShape(Circle())
                        .shadow(
                            color: Color.black.opacity(0.3),
                            radius: 3,
                            x: 3,
                            y: 3
                        )
                    }
                }
                .padding()
            }
            .navigationTitle("Bank Account")
            .onAppear {
                viewModel.startObserving()
                viewModel.initAccount()
            }
            .onDisappear {
                viewModel.dispose()
            }
        } else {
            AddPinScreen()
        }
    }
}

struct BankAccountScreen_Previews: PreviewProvider {
    static var previews: some View {
        BankAccountScreen()
    }
}
