import SwiftUI
import shared

struct NumberVerification: View {
    let state: RegisterState
    let onEvent: (RegisterEvent) -> Void
    
    var body: some View {
        VStack {
            Group {
                RegisterHeader(
                    currentSection: state.currentSection,
                    onBack: {
                        onEvent(
                            .UpdateSection(section: .createaccount)
                        )
                    }
                )
                
                Spacer()
                
                Image("register_otp")
                    .resizable()
                    .frame(width: 200, height: 200)
                
                Spacer()
            }
            
            HStack {
                VStack(alignment: .leading) {
                    Text("Verification Code")
                        .font(.title2)
                    
                    Spacer()
                        .frame(height: 2)
                    
                    Text("Please enter the OTP we sent to WhatsApp \(state.number)")
                        .font(.subheadline)
                }
                
                Spacer()
            }
            
            Spacer()
                .frame(height: 32)
            
            DefaultTextField(
                title: "OTP",
                text: Binding(
                    get: {
                        state.otp
                    },
                    set: {
                        if state.otp.count < 6 {
                            onEvent(
                                .OnOtpChange(otp: $0)
                            )
                        }
                    }
                )
            )
            .keyboardType(.numberPad)
            
            if let numberError = state.numberError {
                HStack {
                    Text(numberError.description())
                        .font(.caption)
                        .foregroundColor(.red)
                    
                    Spacer()
                }
            }
            
            Spacer()
                .frame(height: 32)
            
            Button {
                onEvent(
                    .OnVerifyOtp()
                )
            } label: {
                Text("SUBMIT")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            .disabled(!state.sendOtpEnabled)
            
            HStack {
                Text("Didn't receive the OTP?")
                
                Button {
                    
                } label: {
                    Text("Resend")
                }
            }
            
            Spacer()
        }
        .padding()
    }
}

struct NumberVerification_Previews: PreviewProvider {
    static var previews: some View {
        NumberVerification(
            state: RegisterState(
                currentSection: .numberverification,
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
