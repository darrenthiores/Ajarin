import SwiftUI

struct FilterItem: View {
    let text: String
    let isSelected: Bool
    
    var body: some View {
        Text(text)
            .padding()
            .background(
                isSelected ? .purple.opacity(0.2) : Color(UIColor.systemBackground)
            )
            .cornerRadius(8)
            .overlay(
                RoundedRectangle(cornerRadius: 8)
                    .stroke(
                        isSelected ? .purple : .gray,
                        lineWidth: 1
                    )
            )
    }
}

struct FilterItem_Previews: PreviewProvider {
    static var previews: some View {
        FilterItem(
            text: "Jakarta Utara",
            isSelected: true
        )
    }
}

