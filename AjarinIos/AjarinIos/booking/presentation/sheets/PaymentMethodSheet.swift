import SwiftUI
import shared

let methods = [
    PaymentMethod(
        id: "0",
        name: "Dana"
    ),
    PaymentMethod(
        id: "1",
        name: "Gopay"
    ),
    PaymentMethod(
        id: "2",
        name: "Shopee Pay"
    ),
    PaymentMethod(
        id: "3",
        name: "Ovo"
    )
]

struct PaymentMethodSheet: View {
    let selectMethod: (PaymentMethod) -> Void
    
    var body: some View {
        List {
            Text("Select Method")
                .padding(.vertical)
                .listRowSeparator(.hidden)
            
            ForEach(methods, id: \.id) { method in
                Button {
                    selectMethod(method)
                } label: {
                    HStack {
                        Image(method.getLogo())
                            .resizable()
                            .scaledToFit()
                            .frame(width: 50, height: 50)
                        
                        Spacer()
                            .frame(width: 32)
                        
                        Text(method.name)
                    }
                }
                .buttonStyle(.plain)
            }
        }
        .listStyle(.plain)
    }
}

struct PaymentMethodSheet_Previews: PreviewProvider {
    static var previews: some View {
        PaymentMethodSheet(
            selectMethod: { _ in }
        )
    }
}
