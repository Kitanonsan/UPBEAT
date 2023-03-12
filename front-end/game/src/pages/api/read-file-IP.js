import fs from "fs";
import path from "path";

export default function handler(req, res) {
  // Get the path to the IP.json file
  const filePath = path.join(process.cwd(), "IP.json");

  // Read the file content
  try {
    const fileContent = fs.readFileSync(filePath, "utf-8");
    const data = JSON.parse(fileContent);

    // Return the file content as the API response
    res.status(200).json(data);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error reading file");
  }
}
