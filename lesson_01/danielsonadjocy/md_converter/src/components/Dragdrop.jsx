import { useRef, useState} from 'react';
import "./Dragdrop.css";
import { FaUpload, FaDownload, FaFileAlt} from 'react-icons/fa';




const DragDrop = () => {

    const wrapperRef = useRef(null);

    const [fileList, setFileList] = useState([]);
    

    const onDragEnter = () => wrapperRef.current.classList.add('dragover');
    const onDragLeave = () => wrapperRef.current.classList.remove('dragover');
    const onDrop = () => wrapperRef.current.classList.remove('dragover');

    const onFileDrop = (e) => {
        const newFiles = Array.from(e.target.files);

        const filtered = newFiles.filter(
            f => !fileList.some(existing => existing.name === f.name)
        );
        if (filtered.length == 0){
            return;
        }
        setFileList(prev => [...prev, ...filtered]);
    }

    const removeFile = (file) => {
        const updatedList = [...fileList];
        updatedList.splice(fileList.indexOf(file), 1);
        setFileList(updatedList);
    }

    const handleDownload = async () =>{
        if (fileList.length == 0){
            return;
        }
        const formData = new FormData();
        fileList.forEach(file => {
            if (file.name.toLowerCase().endsWith(".md")){
                formData.append("markdown", file);
            }
            else{
                formData.append("files", file)
            }
        });
        try {
            const response = await fetch('http://localhost:5001/convert', {
            method: "POST",
            body: formData
            });

            if (!response.ok){
                throw new Error("Conversion failed");
            }

            const blob = await response.blob();
            
            const url = window.URL.createObjectURL(blob);

            const a = document.createElement("a");
            a.href = url;
            a.download = fileList.length === 1 ? fileList[0].name.replace(/\.md$/, ".html") : "converted_files.zip";
            a.click();

        } catch (err) {
            console.error(err)
        }
    }


    return (
        <>
            <div
                ref={wrapperRef}
                className="drop-file-input"
                onDragEnter={onDragEnter}
                onDragLeave={onDragLeave}
                onDrop={onDrop}
            >
                <div className="drop-file-input__label">
                    <FaUpload style={{ fontSize: '10rem'}}/>
                    <p>Drag & Drop your files here</p>
                </div>
                <input type="file" multiple onChange={onFileDrop} className='upload-download'/>
            </div>

            {
                fileList.length > 0 && (

                <div>
                    <p>Files ready:</p>
                    {fileList.map(file => (
                        <div key={`${file.name}`} className="drop-file-preview__item">
                        <FaFileAlt/>
                        {file.name}
                        <span className="drop-file-preview__item__del" onClick={() => removeFile(file)}>x</span>
                        </div>
                    ))}
                    <div
                        ref={wrapperRef}
                        className="drop-file-input"
                        onDragEnter={onDragEnter}
                        onDragLeave={onDragLeave}
                        onDrop={onDrop}
                    >
                        <div className="drop-file-input__label">
                            <FaDownload style={{ fontSize: '10rem'}}/>
                            <p>Download file(s)</p>
                        </div>
                        <button className='upload-download' onClick={handleDownload}></button>
                    </div>
                </div>       
            )}
        </>
    );
}

export default DragDrop;