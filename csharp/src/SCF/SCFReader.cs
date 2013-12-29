using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
namespace Serengetti.SCF {
    /// <summary>
    /// Reads from SCF. SCF stands for Serengetti Config Format.
    /// </summary>
    public class SCFReader {
        Stream stream;
        /// <summary>
        /// Initialize an <code>SCFReader</code> to read from the specified path.
        /// </summary>
        /// <param name="path"></param>
        public SCFReader(String path) {
            stream = new FileStream(path, FileMode.Open);
        }
        /// <summary>
        /// Initialize an <code></code> with the specified stream to derive data from.
        /// </summary>
        /// <param name="stream">The stream to read from</param>
        public SCFReader(Stream stream) {
            if (!stream.CanRead) {
                throw new ArgumentException("Cannot read from a stream that can't read!");
            }
            this.stream = stream;
        }
        /// <summary>
        /// Close the <code>SCFReader</code>.
        /// </summary>
        /// <param name="closeStream">Whether or not to close the stream. At the moment, it will do nothing if this is false.</param>
        public void close(bool closeStream) {
            stream.Close();
        }
        /// <summary>
        /// Process the file or stream.
        /// </summary>
        /// <returns>The resulting dictionary.</returns>
        public Dictionary<String, String> process() {
            StreamReader reader = new StreamReader(stream);
            Dictionary<String, String> values = new Dictionary<String, String>();
            int index = 0;
            for (String s = reader.ReadLine(); s != null; s = reader.ReadLine()) {
                index++;
                //Check for blank lines and ignore them
                if (s == "") {
                    continue;
                }
                String[] nameValue = s.Split(':');
                if (nameValue.Count() < 2) {
                    throw new SyntaxException(index, "Error processing SCF. The name/value pair on line " + index + " did not have a value.");
                } else if (nameValue.Count() > 2) {
                    for (int i = 2; i < nameValue.Count(); i++) {
                        nameValue[1] += ":" + nameValue[i];
                    }
                }
                values[nameValue[0]] = nameValue[1];
            }
            /*List<String> lines = new List<String>();
            byte[] bytes = new byte[1024];
            bool loop = true;
            int index = 0;
            while (loop) {
                int i = stream.ReadByte();
                if (i == -1) {
                    //OutputHandler.output(Importance.INFO, "Found end of file.", true);
                    loop = false;
                    char[] chars = new char[index];
                    for (int j = 0; j < chars.Length; j++) {
                        chars[j] = (char)bytes[j];
                    }
                    String s = new String(chars);
                    s.Replace('\0', new char());
                    lines.Add(s);
                    index = 0;
                } else {
                    bytes[index] = (byte) i;
                }
                if (bytes[index] == '\n') {
                    char[] chars = new char[index];
                    for (int j = 0; j < chars.Length; j++) {
                        chars[j] = (char) bytes[j];
                    }
                    String s = new String(chars);
                    s.Replace('\0', new char());
                    lines.Add(s);
                    index = 0;
                    //OutputHandler.output(Importance.INFO, "Found new line.", true);
                    bytes = new byte[1024];
                }
                index++;
            }*/
            //Dictionary<String, String> values = new Dictionary<String, String>();
            /*foreach (String line in lines) {
                //OutputHandler.output(Importance.INFO, "Processing line #" + lines.IndexOf(line) + " with value " + line + ".");
                char[] chars = new char[1];
                chars[0] = ':';
                string[] parts = line.Split(chars);
                if (parts.Length != 2) {
                    
                }
                values.Add(parts[0], parts[1]);
            }*/
            return values;
        }
    }
}
