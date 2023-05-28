import SwiftUI
import shared

struct FillNumber: View {
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
                
                Image("register_phone")
                    .resizable()
                    .frame(width: 200, height: 200)
                
                Spacer()
            }
            
            HStack {
                VStack(alignment: .leading) {
                    Text("Fill in your phone number")
                        .font(.title2)
                    
                    Spacer()
                        .frame(height: 2)
                    
                    Text("Please enter the phone number we will send the OTP in this phone number")
                        .font(.subheadline)
                }
                
                Spacer()
            }
            
            Spacer()
                .frame(height: 32)
            
            DefaultTextField(
                title: "Phone Number",
                text: Binding(
                    get: {
                        state.number
                    },
                    set: {
                        onEvent(
                            .OnNumberChange(number: $0)
                        )
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
                    .OnSendOtpResult(
                        result: ResourceSuccess(data: "")
                    )
                )
            } label: {
                Text("GET OTP")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            .disabled(!state.sendOtpEnabled)
            
            Spacer()
        }
        .padding()
    }
}

struct FillNumber_Previews: PreviewProvider {
    static var previews: some View {
        FillNumber(
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
