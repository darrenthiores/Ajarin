//
//  WithdrawSuccessScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WithdrawSuccessScreen: View {
    @Environment(\.dismiss) var dismiss
    let onBack: () -> Void
    
    var body: some View {
        VStack {
            Image(systemName: "checkmark")
                .resizable()
                .scaledToFit()
                .padding(64)
                .foregroundColor(.green)
            
            Text("Withdraw Successful, Please Check Your Bank Account Balance!")
                .multilineTextAlignment(.center)
            
            Spacer()
                .frame(height: 32)
            
            Button {
                dismiss()
                onBack()
            } label: {
                HStack {
                    Spacer()
                    
                    Text("Back")
                    
                    Spacer()
                }
            }
            .buttonStyle(.borderedProminent)
        }
        .padding()
        .navigationTitle("Withdraw Success")
        .navigationBarBackButtonHidden(true)
    }
}

struct WithdrawSuccessScreen_Previews: PreviewProvider {
    static var previews: some View {
        WithdrawSuccessScreen(
            onBack: {  }
        )
    }
}
