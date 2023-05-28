import SwiftUI
import shared

struct RegisterHeader: View {
    let currentSection: RegisterSection
    let onBack: () -> Void
    
    var body: some View {
        HStack {
            Button {
                onBack()
            } label: {
                Image(systemName: "chevron.left")
            }
            
            ZStack {
                Color.primary
            }
            .frame(height: 10)
            .clipShape(
                RoundedRectangle(cornerSize: CGSize(width: 8, height: 8))
            )
            
            ZStack {
                if case .numberverification = currentSection {
                    Color.primary
                } else {
                    Color.gray
                }
            }
            .frame(height: 10)
            .clipShape(
                RoundedRectangle(cornerSize: CGSize(width: 8, height: 8))
            )
        }
    }
}

struct RegisterHeader_Previews: PreviewProvider {
    static var previews: some View {
        RegisterHeader(
            currentSection: .fillnumber,
            onBack: {  }
        )
    }
}
