import SwiftUI
import shared

struct CreateAccount: View {
    let state: RegisterState
    let onEvent: (RegisterEvent) -> Void
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        VStack {
            Spacer()
            
            HStack {
                VStack(alignment: .leading) {
                    Text("Create Account")
                        .font(.title2)
                    
                    Spacer()
                        .frame(height: 2)
                    
                    Text("Create an account to access all features in this app.")
                        .font(.subheadline)
                }
                
                Spacer()
            }
            
            Spacer()
                .frame(height: 32)
            
            Group {
                DefaultTextField(
                    title: "Username",
                    text: Binding(
                        get: {
                            state.name
                        },
                        set: {
                            onEvent(
                                .OnNameChange(name: $0)
                            )
                        }
                    )
                )
                
                if let nameError = state.nameError {
                    HStack {
                        Text(nameError.description())
                            .font(.caption)
                            .foregroundColor(.red)
                        
                        Spacer()
                    }
                }
                
                Spacer()
                    .frame(height: 8)
                
                DefaultTextField(
                    title: "Email",
                    text: Binding(
                        get: {
                            state.email
                        },
                        set: {
                            onEvent(
                                .OnEmailChange(email: $0)
                            )
                        }
                    )
                )
                
                if let emailError = state.emailError {
                    HStack {
                        Text(emailError.description())
                            .font(.caption)
                            .foregroundColor(.red)
                        
                        Spacer()
                    }
                }
                
                Spacer()
                    .frame(height: 8)
                
                PasswordTextField(
                    hint: "Password",
                    passwordVisible: Binding(
                        get: {
                            state.isPasswordVisible
                        },
                        set: {_ in
                            onEvent(
                                .ToggleShowPassword()
                            )
                        }
                    ),
                    text: Binding(
                        get: {
                            state.password
                        },
                        set: {
                            onEvent(
                                .OnPasswordChange(password: $0)
                            )
                        }
                    )
                )
                
                if let passwordError = state.passwordError {
                    HStack {
                        Text(passwordError.description())
                            .font(.caption)
                            .foregroundColor(.red)
                        
                        Spacer()
                    }
                }
            }
            
            Spacer()
                .frame(height: 32)
            
            Button {
                onEvent(
                    .UpdateSection(section: .fillnumber)
                )
            } label: {
                Text("Next")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            .disabled(!state.registerEnabled)
            
            HStack {
                Text("Already have an account?")
                
                Button {
                    presentationMode.wrappedValue.dismiss()
                } label: {
                    Text("Sign In")
                }
            }
            
            Spacer()
        }
        .padding()
    }
}

struct CreateAccount_Previews: PreviewProvider {
    static var previews: some View {
        CreateAccount(
            state: RegisterState(
                currentSection: .createaccount,
                name: "",
                nameError: nil,
                email: "",
                emailError: nil,
                password: "",
                passwordError: nil,
                isPasswordVisible: false,
                registerEnabled: false,
                number: "",
                numberError: nil,
                sendOtpEnabled: false,
                otp: "",
                otpError: nil,
                verifyOtpEnabled: false,
                otpSendCount: 0,
                otpCountDown: 0,
                verificationId: nil,
                sendOtpError: nil,
                sendOtpLoading: false,
                verifyOtpError: nil,
                verifyOtpLoading: false,
                registerLoading: false,
                registerError: nil,
                registerSuccess: false
            ),
            onEvent: { _ in }
        )
    }
}
