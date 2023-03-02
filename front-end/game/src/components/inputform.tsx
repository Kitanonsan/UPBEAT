import React, { useState } from "react";
import AceEditor from "react-ace";
import "ace-builds/src-noconflict/mode-javascript";
import "ace-builds/src-noconflict/theme-monokai";

function InputForm() {
  const [userInput, setUserInput] = useState("");

  const handleChange = (newInput: string) => {
    setUserInput(newInput);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // Do something with userInput
    console.log(userInput);
  };

  return (
    <form onSubmit={handleSubmit}>
      <AceEditor
        mode="javascript"
        theme="monokai"
        onChange={handleChange}
        name="userInput"
        editorProps={{ $blockScrolling: true }}
        fontSize={18}
        style={{ height: "300px", width: "220%" }}
      />
      <button type="submit">Submit</button>
    </form>
  );
}

export default InputForm;
