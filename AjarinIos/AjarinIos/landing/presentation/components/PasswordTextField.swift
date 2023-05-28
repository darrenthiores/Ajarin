import SwiftUI

struct PasswordTextField: View {
    let hint: String
    @Binding var passwordVisible: Bool
    @Binding var text: String
    
    var body: some View {
        HStack {
            if passwordVisible {
                TextField(
                    hint,
                    text: $text
                )
            } else {
                SecureField(
                    hint,
                    text: $text
                )
            }
        }
        .overlay(alignment: .trailing) {
            Image(systemName: !passwordVisible ? "eye.slash" : "eye")
                .onTapGesture {
                    passwordVisible.toggle()
                }
        }
        .padding()
        .overlay(
            Rectangle()
                .strokeBorder(
                    .gray.opacity(0.2),
                    style: StrokeStyle(lineWidth: 2.0)
                )
        )
    }
}

struct PasswordTextField_Previews: PreviewProvider {
    static var previews: some View {
        PasswordTextField(
            hint: "Password",
            passwordVisible: .constant(false),
            text: .constant("test123")
        )
    }
}

