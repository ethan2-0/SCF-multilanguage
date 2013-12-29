using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Serengetti.SCF {
    public class SyntaxException : Exception {
        public SyntaxException(int line) : base("A syntax error occurred on line " + line + ". THIS IS NOT A .NET ERROR. THIS IS AN ERROR FOR INTERNAL CODE.") {

        }
        public SyntaxException(int line, String msg) : base("A syntax error occurred on line " + line + ": " + msg + ". THIS IS NOT A .NET ERROR. THIS IS AN ERROR FOR INTERNAL CODE."){
            
        }
    }
}
