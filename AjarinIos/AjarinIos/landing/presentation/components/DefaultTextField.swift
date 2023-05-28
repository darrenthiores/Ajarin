import SwiftUI

struct DefaultTextField: View {
    let title: String
    @Binding var text: String
    
    var body: some View {
        TextField(
            title,
            text: $text
        )
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

struct DefaultTextField_Previews: PreviewProvider {
    static var previews: some View {
        DefaultTextField(
            title: "Mobile No",
            text: .constant("")
        )
    }
}
