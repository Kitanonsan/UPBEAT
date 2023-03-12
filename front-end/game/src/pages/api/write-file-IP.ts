import fs from "fs";
import { NextApiRequest, NextApiResponse } from "next";

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  const { inputValue } = req.body;
  fs.writeFile("IP.json", inputValue, function (err) {
    if (err) {
      console.error(err);
      res.status(500).send("Error writing file");
    } else {
      console.log("Input saved!");
      res.status(200).send("File written successfully");
    }
  });
}
