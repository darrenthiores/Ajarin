import SwiftUI

struct InformationItem: View {
    let iconName: String
    let text: String
    
    var body: some View {
        HStack {
            Image(systemName: iconName)
            
            Text(text)
        }
        .padding(8)
        .background(
            RoundedRectangle(
                cornerSize: CGSize(width: 4, height: 4)
            )
            .fill(.gray)
        )
    }
}

struct InformationItem_Previews: PreviewProvider {
    static var previews: some View {
        InformationItem(
            iconName: "hand.thumbsup.fill",
            text: "4.5"
        )
    }
}
