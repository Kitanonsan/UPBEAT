import React, { useState } from "react";
import AceEditor from "react-ace";

import "ace-builds/src-noconflict/mode-javascript";
import "ace-builds/src-noconflict/theme-monokai";

function CodeEditor() {
  const [code, setCode] = useState("");

  const handleCodeChange = (newCode) => {
    setCode(newCode);
  };

  return (
    <div>
      <h1>Code Editor</h1>
      <AceEditor
        mode="javascript"
        theme="monokai"
        value={code}
        onChange={handleCodeChange}
        name="code-editor"
        editorProps={{ $blockScrolling: true }}
        fontSize={14}
        showPrintMargin={true}
        showGutter={true}
        highlightActiveLine={true}
        style={{ width: "100%", minHeight: "400px" }}
      />
      <button onClick={() => console.log(code)}>Log Code</button>
    </div>
  );
}

export default CodeEditor;
