import SwiftUI

struct ProfileButton: View {
    let text: String
    let iconName: String
    let onClick: () -> Void
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                Image(systemName: iconName)
                
                Spacer()
                    .frame(width: 16)
                
                Text(text)
                    .fontWeight(.bold)
                    .font(.subheadline)
                
                Spacer()
            }
            .padding()
            .contentShape(Rectangle())
            .overlay(
                RoundedRectangle(cornerRadius: 6)
                    .stroke(.gray)
            )
        }
        .buttonStyle(.plain)
    }
}

struct ProfileButton_Previews: PreviewProvider {
    static var previews: some View {
        ProfileButton(
            text: "Account",
            iconName: "person.circle",
            onClick: {  }
        )
    }
}
