import SwiftUI

struct LoginScreen: View {
    @StateObject private var viewModel = IosLoginViewModel()
    
    var body: some View {
        NavigationStack {
            VStack {
                Spacer()
                
                HStack {
                    VStack(alignment: .leading) {
                        Text("Login")
                            .font(.title2)
                        
                        Spacer()
                            .frame(height: 2)
                        
                        Text("Login to your account and restore all your data")
                            .font(.subheadline)
                    }
                    
                    Spacer()
                }
                
                Spacer()
                    .frame(height: 32)
                
                Group {
                    DefaultTextField(
                        title: "Email",
                        text: Binding(
                            get: {
                                viewModel.state.email
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnEmailChange(email: $0)
                                )
                            }
                        )
                    )
                    
                    if let emailError = viewModel.state.emailError {
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
                                viewModel.state.isPasswordVisible
                            },
                            set: {_ in
                                viewModel.onEvent(
                                    event: .ToggleShowPassword()
                                )
                            }
                        ),
                        text: Binding(
                            get: {
                                viewModel.state.password
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnPasswordChange(password: $0)
                                )
                            }
                        )
                    )
                    
                    if let passwordError = viewModel.state.passwordError {
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
                
                Group {
                    Button {
                        viewModel.onEvent(
                            event: .Login()
                        )
                    } label: {
                        Text("Login")
                            .frame(maxWidth: .infinity)
                    }
                    .buttonStyle(.borderedProminent)
                    
                    HStack {
                        Text("Don't have an account?")
                        
                        NavigationLink {
                            RegisterScreen()
                        } label: {
                            Text("Sign Up")
                        }
                    }
                }
                
                Spacer()
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
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
