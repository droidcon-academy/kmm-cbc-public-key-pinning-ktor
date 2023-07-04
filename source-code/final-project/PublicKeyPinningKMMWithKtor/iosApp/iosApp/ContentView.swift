import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greet()
    
    @State
    private var data:String? = nil
    
    @State
    private var error:String? = nil

    
    var body: some View {
        VStack(alignment: .center, spacing: 20) {
            Text(greet)
            HStack{
                Button(action: fetchDataWithCorrectPins) {
                    Text("Connect with correct pins").padding()
                }
                .frame(maxWidth: .infinity)
                .foregroundColor(Color.white)
                .background(Color.blue)
                .cornerRadius(10)
                .padding(8)
                
                Button(action: fetchDataWithIncorrectPins) {
                    Text("Connect with incorrect pins").padding()
                }
                .frame(maxWidth: .infinity)
                .foregroundColor(Color.white)
                .background(Color.blue)
                .cornerRadius(10)
                .padding(8)
            }
            
            ScrollView{
                Text(data ?? error ?? "No data")
                    .frame(maxWidth: .infinity, alignment: .topLeading)
                    .foregroundColor(data != nil ? Color.green : Color.red)
                    .padding()
                
            }.frame(maxWidth: .infinity,
                    maxHeight: .infinity)
            .background(Color.black)
            .padding(8)
            
            Spacer()
        }
    }
    
    private func fetchData(pinningService: PinningService) {
        pinningService.getData() { result, error in
            if let result = result {
                self.data = result
                self.error = nil
            } else if let error = error {
                self.data = nil
                self.error = "\(error)"
            }
        }
    }
    
    private func fetchDataWithCorrectPins(){
        fetchData(pinningService: CorrectPinningService())
    }
    
    private func fetchDataWithIncorrectPins() {
        fetchData(pinningService: IncorrectPinningService())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
