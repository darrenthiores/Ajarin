//
//  AddPinScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AddPinScreen: View {
    @StateObject private var viewModel = IosAddPinViewModel()
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        VStack {
            HStack {
                VStack(alignment: .leading) {
                    Text("Your Pin")
                        .font(.title2)
                    
                    Spacer()
                        .frame(height: 2)
                    
                    Text("Please enter a Pin to Secure Your Wallet")
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
                    event: .AddPin()
                )
            } label: {
                Text("SAVE")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            .disabled(
                viewModel.state.pin.isEmpty ||
                viewModel.state.pin.count < 6 ||
                viewModel.state.pinError != nil ||
                viewModel.state.addPinLoading
            )
        }
        .padding()
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct AddPinScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddPinScreen()
    }
}
