//
//  BookingSuccessScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BookingSuccessScreen: View {
    @Environment(\.dismiss) var dismiss
    let onBack: () -> Void
    
    var body: some View {
        VStack {
            Image(systemName: "checkmark")
                .resizable()
                .scaledToFit()
                .padding(64)
                .foregroundColor(.green)
            
            Text("Payment Successful, Good luck on your learning journey!")
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
        .navigationTitle("Payment Success")
        .navigationBarBackButtonHidden(true)
    }
}

struct BookingSuccessScreen_Previews: PreviewProvider {
    static var previews: some View {
        BookingSuccessScreen(
            onBack: {  }
        )
    }
}
