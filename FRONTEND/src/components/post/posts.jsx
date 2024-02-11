
import {useEffect, useState} from "react";
import PostCard from "./postCard.jsx";
import PostSearch from "./postSearch.jsx";
import {getPosts} from "../services/PostService.jsx";

function Posts() {
    const [posts, setPosts] = useState({});

    const fetchPosts = async () => {
        try {
            setPosts(await getPosts());
        } catch (e) {
            console.log(e)
        }
    }
    useEffect(() => {
        fetchPosts()
    }, []);

    return(
        <>
            <PostSearch/>
            {posts.length ? (
                <>
                    {posts.map((post) => (
                        <PostCard key={post.id} post={post}/>
                    ))}
                </>
            ) : (
                <p>
                    <i>No job posts.</i>
                </p>
            )}
        </>
    )
}

export default Posts