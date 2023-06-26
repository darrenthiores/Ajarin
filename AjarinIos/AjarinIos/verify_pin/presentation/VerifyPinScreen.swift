//
//  VerifyPinScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct VerifyPinScreen: View {
    @StateObject private var viewModel = IosVerifyPinViewModel()
    @Environment(\.dismiss) var dismiss
    let onDismiss: () -> Void
    
    var body: some View {
        VStack {
            HStack {
                VStack(alignment: .leading) {
                    Text("Your Pin")
                        .font(.title2)
                    
                    Spacer()
                        .frame(height: 2)
                    
                    Text("Please enter a Pin to Complete Your Withdrawal")
                        .font(.subheadline)
                }
                
                Spacer()
            }
            
            Spacer()
                .frame(height: 32)
            
            DefaultTextField(
                title: "Pin",
                text: Binding(
                    get: {
                        viewModel.state.pin
                    },
                    set: {
                        if viewModel.state.pin.count < 6 {
                            viewModel.onEvent(
                                event: .OnPinChange(pin: $0)
                            )
                        }
                    }
                )
            )
            .keyboardType(.numberPad)
            
            if let pinError = viewModel.state.pinError {
                HStack {
                    Text(pinError.description())
                        .font(.caption)
                        .foregroundColor(.red)
                    
                    Spacer()
                }
            }
            
            Spacer()
                .frame(height: 32)
            
            Button {
                viewModel.onEvent(
                    event: .VerifyPin()
                )
            } label: {
                Text("Verify Pin")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            .disabled(
                viewModel.state.pin.isEmpty ||
                viewModel.state.pin.count < 6 ||
                viewModel.state.pinError != nil ||
                viewModel.state.verifyPinLoading
            )
        }
        .navigationTitle("Verify Pin")
        .padding()
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .fullScreenCover(
            isPresented: Binding(
                get: {
                    viewModel.state.verifyPinSuccess
                },
                set: { _ in }
            )
        ) {
            WithdrawSuccessScreen(
                onBack: {
                    dismiss()
                    onDismiss()
                }
            )
        }
    }
}

struct VerifyPinScreen_Previews: PreviewProvider {
    static var previews: some View {
        VerifyPinScreen(
            onDismiss: {  }
        )
    }
}
