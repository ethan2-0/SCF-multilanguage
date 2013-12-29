using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace Serengetti.SCF {
    public class SCFWriter {
        private Stream stream;
        private StreamWriter writer;
        public SCFWriter(Stream stream) {
            this.stream = stream;
            StreamWriter writer = new StreamWriter(stream);
        }
        public SCFWriter(String file) {
            stream = new FileStream(file, FileMode.Create);
            writer = new StreamWriter(stream);
        }
        public void close() {
            writer.Close();
        }
        public void write(Dictionary<String, String> nameValue) {
            foreach (KeyValuePair<String, String> pair in nameValue) {
                writer.WriteLine(pair.Key + ":" + pair.Value);
            }
        }
    }
}
